package com.rms.recruitment.dto.response;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Integer totalElements;
    private Integer totalPages;
    private Integer currentPage;

    // No-args constructor
    public ApiResponse() {
    }

    // All-args constructor
    public ApiResponse(boolean success, String message, T data, Integer totalElements, Integer totalPages,
            Integer currentPage) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Thành công");
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    // Builder pattern methods
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private boolean success;
        private String message;
        private T data;
        private Integer totalElements;
        private Integer totalPages;
        private Integer currentPage;

        public Builder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> totalElements(Integer totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder<T> totalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder<T> currentPage(Integer currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public ApiResponse<T> build() {
            return new ApiResponse<>(success, message, data, totalElements, totalPages, currentPage);
        }
    }
}
