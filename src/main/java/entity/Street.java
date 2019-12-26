package entity;

/**
 * 街道
 *
 * @author kamjin1996
 */
public class Street {
    public Street() {
    }

    public Street(String street, Integer flowNum) {
        this.flowNum = flowNum;
        this.street = street;
    }

    private String street;

    private Integer flowNum;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(Integer flowNum) {
        this.flowNum = flowNum;
    }
}
