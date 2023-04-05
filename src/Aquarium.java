import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Aquarium {

    private Random random = new Random();
    private int numberOfFishToBeCreated = random.nextInt(10);
    private List<Fish> fishList = new ArrayList<>();
    public void start() {
        System.out.println(MyColor.YELLOW + numberOfFishToBeCreated + " fish will be added to aquarium");
        for (int i = 0; i < numberOfFishToBeCreated; i++) {
            var fish = new Fish(false);
            fishList.add(fish);
            fish.start();
        }
        System.out.println(MyColor.GREEN + "Number of male fish in the aquarium : " + getNumberOfTotalFishInTheAquariumByGender(Gender.MALE));
        System.out.println(MyColor.PURPLE + "Number of female fish in the aquarium : " + getNumberOfTotalFishInTheAquariumByGender(Gender.FEMALE));

        while (fishList.size() > 0 && fishList.size() < 20) {
            breed();
            removeDeadFishFromTheList();
            makeFishMature();
            //  System.out.println(MyColor.BlUE+"Number of existing fishes " + fishList.size());
        }
    }

    private void breed() {
        for (int i = 0; i < fishList.size(); i++) {
            for (int j = i; j < fishList.size(); j++) {
                if (Fish.canHaveChild(fishList.get(i), fishList.get(j))) {
                    fishList.get(i).setHaveCouple(true);
                    fishList.get(j).setHaveCouple(true);
                    fishList.add(new Fish(true));
                    //threads.add(new Thread(new Fish(true)));
                    Fish child = new Fish(true);
                    System.out.println(MyColor.GREEN + "New " + child.getGender() + " child whith id:" + child.getFishId() + "  is born ");
                    System.out.println(MyColor.BlUE + "Now " + fishList.size() + " fishes exist in the aquarium ");
                }
            }

            try {
                fishList.get(i).sleep(random.nextInt(1000));
                if (fishList.get(i).isHaveCouple()) {
                    fishList.get(i).setHaveCouple(false);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private long getNumberOfTotalFishInTheAquariumByGender(Gender gender) {
        return fishList.stream()
                .filter(fish -> fish.getGender().equals(gender))
                .count();
    }

    private void removeDeadFishFromTheList() {
        Predicate<Fish> predicate = fish -> System.currentTimeMillis() > (fish.getBornTime() + fish.getLifeTime());
        for (int i = 0; i < fishList.size(); i++) {
            // System.err.println((fishList.get(i).getBornTime() + fishList.get(i).getLifeTime()) - System.currentTimeMillis()  );
            if (predicate.test(fishList.get(i))) {
                fishList.remove(fishList.get(i));
                fishList.get(i).interrupt();
                //fishList.remove(fishList.get(i));
                System.out.println(MyColor.RED + fishList.get(i).getGender() + "  Fish with id: " + fishList.get(i).getFishId() + " is dead");
                }
        }
    }

    private void makeFishMature() {
        for (Fish fish : fishList) {
            var fishLife = System.currentTimeMillis() - fish.getBornTime();
            if (fish.isChild() && fishLife > 2000) {
                if (!fishList.isEmpty()){
                    fish.setChild(false);
                    System.out.println(MyColor.YELLOW + fish.getGender() + "  Fish whith id :" + fish.getFishId() + " " + "  became mature");
                }
              }
        }
    }

}
