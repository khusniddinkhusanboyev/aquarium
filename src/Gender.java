import java.util.Random;

public enum Gender {
    MALE, FEMALE;

    private static final Random random = new Random();

    public static Gender randomGender() {
        Gender[] genders = values();
        return genders[random.nextInt(genders.length)];
    }
}
