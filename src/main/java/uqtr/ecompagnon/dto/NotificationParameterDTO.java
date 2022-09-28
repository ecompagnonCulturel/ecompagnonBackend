package uqtr.ecompagnon.dto;

public enum NotificationParameterDTO {
    SOUND("default"),
    //COLOR("#FF0000");
    COLOR(null);

    private String value;

    NotificationParameterDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
