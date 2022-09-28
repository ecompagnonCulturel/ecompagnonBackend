package uqtr.ecompagnon.dto;

public class FcmResponseDTO {
    private int status;
    private String message;


    public FcmResponseDTO() {
    }
    public FcmResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
