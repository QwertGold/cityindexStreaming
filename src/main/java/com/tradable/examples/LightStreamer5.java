package com.tradable.examples;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.lightstreamer.ls_client.*;
import com.tradable.examples.dto.AccountInformationResponseDTO;
import com.tradable.examples.dto.ApiLogOnRequestDTO;
import com.tradable.examples.dto.ApiLogOnResponseDTO;
import com.tradable.examples.dto.ApiTradingAccountDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Klaus Groenbaek
 *         Created 16/02/16.
 */
public class LightStreamer5 {


    public static void main(String[] args) throws Exception {
        new LightStreamer5().run();
    }


    public final String REST_ENDPOINT = "https://ciapi.cityindex.com/TradingApi";
    public final String STREAMING_ENDPOINT = "https://push.cityindex.com";
    private RestTemplate template = new RestTemplate();
    private String session;
    private HttpHeaders headers;
    private ApiTradingAccountDTO account;

    public void run() throws Exception {

        String userName = "DM438269";
        String password = "trade123";

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

        LSClient client = new LSClient();
        ConnectionInfo info = new ConnectionInfo();
        info.password = session;
        info.user = userName;
        info.pushServerUrl = STREAMING_ENDPOINT;
        info.adapter = "STREAMINGALL";
        client.openConnection(info, new MyConnectionListener());

        String[] fields = {"OrderId", "MarketId", "Status",
                "Type", "OriginalQuantity", "Quantity", "LastChangedTime",
                "ExecutionPrice"};
        ExtendedTableInfo tableInfo = new ExtendedTableInfo(new String[]{"ORDERS"}, "DISTINCT", fields, true);

        HandyTableListener listener = new HandyTableListener() {
            @Override
            public void onUpdate(int i, String s, UpdateInfo updateInfo) {
                System.out.println("Update " + s);
            }

            @Override
            public void onSnapshotEnd(int i, String s) {
                System.out.println("snapshot " + s);
            }

            @Override
            public void onRawUpdatesLost(int i, String s, int i1) {

            }

            @Override
            public void onUnsubscr(int i, String s) {

            }

            @Override
            public void onUnsubscrAll() {

            }
        };
        client.subscribeTable(tableInfo, listener, false);

        TimeUnit.HOURS.sleep(1);
    }


    private HttpHeaders createHeaders(String session, String userName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Session", session);
        headers.add("UserName", userName);
        return headers;
    }


    private URI uri(String relativeUrl) {
        try {
            return new URI(REST_ENDPOINT + relativeUrl);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    private static class MyConnectionListener implements ConnectionListener {
        @Override
        public void onConnectionEstablished() {
            System.out.println("Established");
        }

        @Override
        public void onSessionStarted(boolean b) {
            System.out.println("Session started " + b);
        }

        @Override
        public void onNewBytes(long l) {

        }

        @Override
        public void onDataError(PushServerException e) {

        }

        @Override
        public void onActivityWarning(boolean b) {

        }

        @Override
        public void onClose() {

        }

        @Override
        public void onEnd(int i) {

        }

        @Override
        public void onFailure(PushServerException e) {
            e.printStackTrace();
        }

        @Override
        public void onFailure(PushConnException e) {
            e.printStackTrace();
        }
    }
}
