package cardiff.domain.response;

import lombok.Getter;

@Getter
public class CommonResponse<T> extends BasicResponse {
    private T data;

    public CommonResponse(T data) {
        this.data = data;

    }

    public CommonResponse() {
        this.success = true;
    }
}
