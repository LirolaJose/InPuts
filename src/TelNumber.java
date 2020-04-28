public class TelNumber {
    private String firstNumber;
    private String cityCode;
    private String number;

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
        if(this.firstNumber.contains(" ")){
        this.firstNumber = this.firstNumber.replace(" ", "");
    }
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
