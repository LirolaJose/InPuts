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
        switch (cityCode) {
            case "(101)":
                this.cityCode = "(401)";
                break;
            case "(202)":
                this.cityCode = "(802)";
                break;
            case "(301)":
                this.cityCode = "(321)";
                break;
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
