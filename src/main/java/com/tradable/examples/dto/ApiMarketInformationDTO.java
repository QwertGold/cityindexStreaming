package com.tradable.examples.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.enums.CIMarketSettingsType;
import com.tradable.examples.dto.enums.CIMarketType;
import com.tradable.examples.dto.enums.CIOptionType;
import com.tradable.examples.dto.enums.CIUnderlyingMarketType;
import com.tradable.examples.dto.serializers.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
//@JsonIgnoreProperties(ignoreUnknown = true) // we don't care about the exchange opening hours etc. (yet)
@Data
@Accessors(chain = true)
public class ApiMarketInformationDTO {
    /**
     * The ID of the market.
     */
    private int MarketId;
    /**
     * The market Name
     */
    private String Name;
    /**
     * Unique identifier for the exchange where the underlying is traded.
     */
    private int ExchangeId;
    /**
     * The name of the exchange where the underlying is traded.
     */
    private String ExchangeName;
    /**
     * Margin factor, expressed as points or as a percentage.
     */
    private Double MarginFactor;
    /**
     * The minimum margin factor.
     */
    private Double MinMarginFactor;
    /**
     * Controls if the margin factor is displayed as a percentage or in points. (Percentage=26, and Points=27).
     */
    private int MarginFactorUnits;
    /**
     * The maximum margin factor.
     */
    private Double MaxMarginFactor;
    /**
     * Description of the market type. This can be 'Option Market', 'Ordinary Market', or 'Binary Market'.
     */
    private String MarketType;
    /**
     * Identifier for each of the market types. (Option=1, Ordinary=2 and Binary=4).
     */
    @JsonDeserialize(using = CIMarketTypeDeserializer.class)
    private CIMarketType MarketTypeId;
    /**
     * The minimum distance from the current price you can place an order.
     */
    private Double MinDistance;
    /**
     * The minimum distance unit type. This can be: (Percentage=26, Points=27).
     */
    private int MinDistanceUnits;
    /**
     * Indicates if the option is a Call or a Put option.
     */
    private String OptionType;
    /**
     * ID number indicating the option type: Put=1 and Call=2. This value is null for non-option markets.
     */
    @JsonDeserialize(using = CIOptionTypeDeserializer.class)
    private CIOptionType OptionTypeId;
    /**
     * The strike price of the option. This value is null for non-option markets.
     */
    private Double StrikePrice;
    /**
     * The minimum quantity that can be traded over the web.
     */
    private double WebMinSize;
    /**
     * The max size of an order.
     */
    private Double MaxSize;
    /**
     * Flag indicating whether the market is a 24 hour market.
     */
    private boolean Market24H;
    /**
     * The number of decimal places in the market's price.
     */
    private Integer PriceDecimalPlaces;
    /**
     * Default quote length.
     */
    private Integer DefaultQuoteLength;
    /**
     * Flag indicating whether you can trade this market on the web.
     */
    private boolean TradeOnWeb;
    /**
     * New sell orders will be rejected. Orders resulting in a short open position will be red carded.
     */
    private boolean LimitUp;
    /**
     * New buy orders will be rejected. Orders resulting in a long open position will be red carded.
     */
    private boolean LimitDown;
    /**
     * Cannot open a short position. Equivalent to limit up.
     */
    private boolean LongPositionOnly;
    /**
     * Can only close open positions. Equivalent to both Limit up and Limit down.
     */
    private boolean CloseOnly;
    /**
     * List of market end of day DTOs.
     */
    private List<ApiMarketEodDTO> MarketEod;

    /**
     * Setting to indicate the user's price tolerance for the given market.
     */
    private Integer PriceTolerance;
    /**
     * Multiplier used to calculate the significance of the price tolerance to the appropriate decimal place.
     * NOTE: I though this could be used to calculate the pipPrecision, but the value is always 10000 even for JPY instruments
     */
    private int ConvertPriceToPipsMultiplier;
    /**
     * The ID type of the market setting. (Spread=1, CFD=2, Binary=3, FX=4, Cash=5, All=6).
     */
    @JsonDeserialize(using = CIMarketSettingsTypeDeserializer.class)
    private CIMarketSettingsType MarketSettingsTypeId;
    /**
     * The type of the market setting, i.e. Spread, CFD.
     */
    private String MarketSettingsType;
    /**
     * A short summary of the market name used when presenting the market name on mobile clients.
     */
    private String MobileShortName;
    /**
     * The method used for central clearing, i.e. "No" or "LCH".
     */
    private String CentralClearingType;
    /**
     * The description of the method used for central clearing, i.e. "None" or "London Clearing House".
     */
    private String CentralClearingTypeDescription;
    /**
     * he currency of the market being traded.
     * The id of the markets Quote currency
     */
    private int MarketCurrencyId;
    /**
     * The minimum quantity that can be traded over the Phone.
     */
    private Double PhoneMinSize;
    /**
     * Daily financing amount to be applied at specified time in UTC.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant DailyFinancingAppliedAtUtc;
    /**
     * Next Date and Time at which the End of Day (EOD) capture will run in UTC.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant NextMarketEodTimeUtc;
    /**
     * Market Trading start time on each trading day represented in UTC and local time.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant TradingStartTimeUtc;
    /**
     * Market Trading end time on each trading day represented in UTC and local time.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant TradingEndTimeUtc;
    /**
     * Market Pricing times on given set of working days.
     */
    private List<ApiTradingDayTimesDTO> MarketPricingTimes;
    /**
     * Breaks throughout each trading day (Day is specified as 'DayOfWeek').
     */
    private List<ApiTradingDayTimesDTO> MarketBreakTimes;
    /**
     * Market spreads during each trading day.
     */
    private List<ApiMarketSpreadDTO> MarketSpreads;
    /**
     * The premium paid for a guaranteed order.
     */
    private Double GuaranteedOrderPremium;
    /**
     * The unit type being used for the guaranteed order premium. This can be (MultipleOfQuantity=1, PercentOfConsideration=2).
     */
    private Integer GuaranteedOrderPremiumUnits;
    /**
     * The minimum distance from current market price at which a guaranteed order can be placed.
     */
    private Double GuaranteedOrderMinDistance;
    /**
     * Guaranteed order minimum distance unit type. This can be: (Percentage=26, Points=27).
     */
    private Integer GuaranteedOrderMinDistanceUnits;
    /**
     * Price tolerance units - used with Price Tolerance as a multiplier (i.e. BetPer or Override Bet Per).
     * NOTE: For the FX instruments I have looked at this can be used to calculate the pipPrecision
     */
    private Double PriceToleranceUnits;
    /**
     * Offset minutes to convert UTC times to local times.
     */
    private Integer MarketTimeZoneOffsetMinutes;
    /**
     * The number of units for an instrument per actual traded units.
     */
    private Double QuantityConversionFactor;
    /**
     * The number of cents which make up a dollar trade on the exchange.
     */
    private int PointFactorDivisor;
    /**
     * Bet Per value for CFD and Spread Bet markets.
     */
    private Double BetPer;
    /**
     * Reflects the market underlying type ID of the associated market.
     */
    @JsonDeserialize(using = CIUnderlyingMarketTypeDeserializer.class)
    private CIUnderlyingMarketType MarketUnderlyingTypeId;
    /**
     * Reflects the market underlying type description of the associated market.
     */
    private String MarketUnderlyingType;
    /**
     * Flag indicating whether guaranteed orders are allowed.
     */
    private boolean AllowGuaranteedOrders;
    /**
     * Flag to indicate if stop orders are allowed for inclusion in margin calculation.
     */
    private boolean OrdersAwareMargining;
    /**
     * The minimum for orders aware margining.
     */
    private Double OrdersAwareMarginingMinimum;
    /**
     * The minimum commission charged on trade/order.
     */
    private Double CommissionChargeMinimum;
    /**
     * The commission rate charged on trade/order.
     */
    private Double CommissionRate;
    /**
     * The unit type for the commission rate charged on a trade/order. This can be:
     * Minimum commission for equities - 11
     * Equities commission rate in basis points - 12
     * Commission for indices - 17
     * Equities commission rate in Cents Per Share - 23
     */
    private Integer CommissionRateUnits;
    /**
     * Expiry of the market in UTC.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant ExpiryUtc;
    /**
     * Step margin data for this market.
     */
    private ApiStepMarginDTO StepMargin;
    /**
     * The date and time in UTC for the future rollover of the market.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant FutureRolloverUTC;
    /**
     * Flag to indicate if auto-rollover on the market is allowed.
     */
    private boolean AllowRollover;
    /**
     * Defines the maximum quantity accepted for buy direction trades or orders.
     */
    private Double MaxLongSize;
    /**
     *
     Defines the maximum quantity accepted for sell direction trades or orders.
     */
    private Double MaxShortSize;

    /**
     * Looks like this holds the quote currency
     */
    @Undocumented
    private String MarketSizesCurrencyCode;
    /**
     * The expiry basis ID of the market.
     */
    private Integer ExpiryBasisId;
    /**
     * The expiry basis text of the market.
     */
    private String ExpiryBasisText;

    @Undocumented
    private int Weighting;

    @Undocumented
    private FxFinancingDTO FxFinancing;

    @Undocumented
    private String UnderlyingRicCode;
    @Undocumented
    private String NewsUnderlyingOverrideType;
    @Undocumented
    private String NewsUnderlyingOverrideCode;
}
