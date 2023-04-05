import java.util.Random;

public class Fish extends Thread {
    private static int count=1;
    private static final int MAX_LIFETIME = 7000;
    private Random random = new Random();
    private int fishId;
    private Gender gender;
    private boolean isChild;
    private boolean haveCouple;
    private long lifeTime;
    private long bornTime;

    public Fish(Boolean isChild) {

        this.fishId=count;
        this.gender = Gender.randomGender();
        this.isChild = isChild;
        this.lifeTime = random.nextInt(MAX_LIFETIME);
        this.bornTime = System.currentTimeMillis();
        count++;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isChild() {
        return isChild;
    }

    public boolean isHaveCouple() {
        return haveCouple;
    }

    public void setHaveCouple(boolean haveCouple) {
        this.haveCouple = haveCouple;
    }

    public long getBornTime() {
        return bornTime;
    }

    public long getLifeTime() {
        return lifeTime;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public int getFishId() {
        return fishId;
    }

    @Override
    public void run() {


    }

    public static boolean canHaveChild(Fish fishOne, Fish fishTwo) {

        if (fishOne.haveCouple || fishTwo.haveCouple) {
            return false;
        }

        if (fishOne.isChild() || fishTwo.isChild()) {
            return false;
        }

        if (fishOne.getGender().equals(fishTwo.getGender())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + fishId +
                ", gender=" + gender +
                ", isChild=" + isChild +
                ", haveCouple=" + haveCouple +
                ", lifeTime=" + lifeTime +
                ", bornTime=" + bornTime +
                '}';
    }
}
