package cleanup.work.workcleanup.controller.form;

public enum ExcelType {
    CAR ("차량 입 출고 관리"),
    CARINSURANCE("보험 수리비 명세표");

    private final String displayValue;

    private ExcelType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }


}
