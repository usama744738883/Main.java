import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;


    public static int[] heroesHealth = {280, 270, 250, 230, 300, 260, 290, 280};
    public static int[] heroesDamage = {10, 15, 20, 0, 5, 10, 20, 15,};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void medic() {
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] > 100) {
                int a = 20;
                int b = 60;
                int treat = a + (int) (Math.random() * b);
                heroesHealth[i] = treat = heroesHealth[i];
            }
            System.out.println("Medic heal: " + heroesAttackType[i]);
            break;

        }
    }

    public static void defenceGolem() {
        int damage = bossDamage / 5;
        int allhealth = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 5) {
                continue;
            } else if (heroesHealth[i] > 0) {
                allhealth++;
                heroesHealth[i] += damage;
            }
            heroesHealth[4] -= damage * allhealth;
            System.out.println("lucky" + damage * allhealth);
            break;
        }

    }

    public static void lucky() {
        Random random = new Random();
        boolean chance = random.nextBoolean();
        if (heroesHealth[5] > 0 && chance) {
            heroesHealth[5] += bossDamage - bossDamage / 5;
        }
        System.out.println("Укланение:" + chance);
    }

    public static void berserkShoot() {
        Random random = new Random();
        int randomDamage = random.nextInt(15) + 1;
        int randomC = random.nextInt(3) + 1;
        if (heroesHealth[5] > 0 && bossHealth > 0) {
            switch (randomC) {
                case 1:
                    heroesDamage[5] = (heroesDamage[5] + bossDamage) - randomDamage;
                    System.out.println("Berserk CRITICAL DAMAGE");
                    System.out.println("При блокировки урон увеличен у берсерка" + randomDamage);
                    break;
                case 2:
                    bossDamage = 50;
                    break;
                case 3:
                    bossDamage = 50;
                    break;
            }
        }
    }

    public static void Thor() {
        Random random = new Random();
        boolean Thoor = random.nextBoolean();
        if (Thoor) {
            bossDamage = 0;
            System.out.println("Босс оглушён");
        } else {
                  bossDamage=50;
            }
        }


        public static void round () {
            roundNumber++;
            chooseBossDefence();
            bossHits();
            heroesHit();
            printStatistics();
            medic();
            defenceGolem();
            lucky();
            berserkShoot();
            Thor();
        }

        public static void bossHits () {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                }
            }
        }

        public static void heroesHit () {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    int damage = heroesDamage[i];
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random random = new Random();
                        int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                        damage = heroesDamage[i] * coeff;
                        System.out.println("Critical damage: " + damage);
                    }
                    if (bossHealth - damage < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - damage;
                    }
                }
            }
        }

        public static void printStatistics () {
            System.out.println("ROUND " + roundNumber + " ----------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
            System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                    + " defence: "
                    + (bossDefenceType == null ? "No defence" : bossDefenceType));
            for (int i = 0; i < heroesHealth.length; i++) {
                System.out.println(heroesAttackType[i] + " health: "
                        + heroesHealth[i] + " damage: " + heroesDamage[i]);
            }
        }

        public static boolean isGameFinished () {
            if (bossHealth <= 0) {
                System.out.println("Heroes won!");
                return true;
            }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!");
            return true;
        }
        return false;*/
            boolean allHeroesDead = true;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    allHeroesDead = false;
                    break;
                }
            }
            if (allHeroesDead) {
                System.out.println("Boss won!");
            }
            return allHeroesDead;
        }
    }
