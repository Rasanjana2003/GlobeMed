package security;

public class AccessResult {
    private boolean granted;
    private String message;
    private boolean maskSensitiveData; 

    public AccessResult(boolean granted, String message, boolean maskSensitiveData) {
        this.granted = granted;
        this.message = message;
        this.maskSensitiveData = maskSensitiveData;
    }
    
    public AccessResult(boolean granted, String message) {
        this.granted = granted;
        this.message = message;      
    }

    public boolean isGranted() {
        return granted;
    }

    public String getMessage() {
        return message;
    }

    public boolean shouldMaskSensitiveData() {
        return maskSensitiveData;
    }
}
