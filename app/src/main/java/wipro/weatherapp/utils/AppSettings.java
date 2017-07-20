package wipro.weatherapp.utils;


import wipro.weatherapp.holders.City;

public class AppSettings {

    public static Unit mSelectedUnit = Unit.Celsius;
    public static City mSelectedCity = new City("London");

    public enum Unit {
        Celsius("metric", "\u2103"), Fahrenheit("imperial", "\u2109");

        public String mParamValue;
        public String mSymbol;

        Unit(String paramValue, String symbol) {
            this.mParamValue = paramValue;
            this.mSymbol = symbol;
        }
    }

}
