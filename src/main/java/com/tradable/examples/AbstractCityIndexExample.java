package com.tradable.examples;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.lightstreamer.client.LightstreamerClient;
import com.tradable.examples.dto.*;
import com.tradable.examples.dto.enums.CIOrderStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Klaus Groenbaek
 *         Created 10/02/16.
 */
public abstract class AbstractCityIndexExample {

    public final String REST_ENDPOINT = "https://ciapi.cityindex.com/TradingApi";
    public final String STREAMING_ENDPOINT = "https://push.cityindex.com";
    private RestTemplate template = new RestTemplate();
    private String session;
    private HttpHeaders headers;
    private ApiTradingAccountDTO account;
    private PriceSubscriptionManager priceSubscriptionManager = new PriceSubscriptionManager();

    public void runExample() throws Exception {

        String userName = "DM438269";
        String password = "trade123";

        LightstreamerClient lightStreamClient = getLightstreamerClient(userName, password);
        priceSubscriptionManager.setClient(lightStreamClient);
        run(lightStreamClient);

        int c;
        do {
            try {
                System.out.println("Click q to quit");
                c = System.in.read();
            } catch (IOException e) {
                break;
            }
        } while (c != 'q');
        System.out.println("Quitting....");
        lightStreamClient.disconnect();
        System.exit(0);

    }

    private LightstreamerClient getLightstreamerClient(String userName, String password) {


        ApiLogOnRequestDTO request = new ApiLogOnRequestDTO().setAppVersion("1").setAppKey("tradable").setUserName(userName)
                .setPassword(password);
        ResponseEntity<ApiLogOnResponseDTO> response = template.postForEntity(uri("/session"), request, ApiLogOnResponseDTO.class);
        session = response.getBody().getSession();
        headers = createHeaders(session, userName);


        RequestEntity entity = new RequestEntity(headers, HttpMethod.GET, uri("/useraccount/ClientAndTradingAccount"));
        AccountInformationResponseDTO accountResponse = template.exchange(entity, AccountInformationResponseDTO.class).getBody();
        List<ApiTradingAccountDTO> tradingAccounts = accountResponse.getTradingAccounts();
        if (tradingAccounts.size() != 1) {
            throw new IllegalStateException("Example only works if the login has a single account.");
        }
        account = tradingAccounts.get(0);

        LightstreamerClient lightStreamClient = new LightstreamerClient(STREAMING_ENDPOINT, "STREAMINGALL");
        lightStreamClient.connectionDetails.setUser(userName);
        lightStreamClient.connectionDetails.setPassword(session);
        lightStreamClient.addListener(new MyClientListener());
        return lightStreamClient;
    }

    protected abstract void run(LightstreamerClient lightStreamClient) throws Exception;

    private URI uri(String relativeUrl) {
        try {
            return new URI(REST_ENDPOINT + relativeUrl);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    private HttpHeaders createHeaders(String session, String userName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Session", session);
        headers.add("UserName", userName);
        return headers;
    }

    /**
     * Places a trade (Market Order). Throws an exception if the order is rejected.
     * @param side the trade direction
     * @param amount the amount
     * @param market the market
     * @return the order Id of the trade
     */
    protected int placeTrade(JSONOrderSide side, int amount, int market) {

        NewTradeOrderRequestDTO dto = new NewTradeOrderRequestDTO();
        dto.setDirection(side);
        dto.setQuantity(amount);
        dto.setMarketId(market);
        // we need the latest price in order to place a trade
        PriceSubscriptionManager.LastPrice lastPrice;
        do {
            lastPrice  = priceSubscriptionManager.getLastPrice(dto.getMarketId());
            if (lastPrice == null) {
                sleep(100);
            }
        } while (lastPrice == null);

        dto.setAuditId(lastPrice.auditId);
        dto.setBidPrice(lastPrice.bid);
        dto.setOfferPrice(lastPrice.offer);

        dto.setTradingAccountId(account.getTradingAccountId());
        RequestEntity<NewTradeOrderRequestDTO> requestEntity = new RequestEntity<>(dto, headers, HttpMethod.POST, uri("/order/newtradeorder"));
        ResponseEntity<ApiTradeOrderResponseDTO> orderResponse = template.exchange(requestEntity, ApiTradeOrderResponseDTO.class);
        ApiTradeOrderResponseDTO response = orderResponse.getBody();
        if (response.getStatus() == CIOrderStatus.OPEN || response.getStatus() == CIOrderStatus.PENDING || response.getStatus() == CIOrderStatus.ACCEPTED) {
            System.out.println("Trade Placed " + response);
        } else {
            throw new IllegalStateException("Order rejected, status " + response.getStatus() + ", reason code " + response.getStatusReason());
        }
        return response.getOrderId();
    }

    protected void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted", e);
        }
    }
}
