package cardiff.domain.response;

import lombok.Getter;

@Getter
public class ErrorResponse extends BasicResponse{
    private String errorMessage;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.success = false;
    }
}
