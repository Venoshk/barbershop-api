package barbershop_api.barbershop.Enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    BARBER("barber");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
