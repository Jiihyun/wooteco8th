package store.dto.request;

public record OrderRequest(
        String productName,
        int quantity
) {
}
