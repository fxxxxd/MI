package MobileThird;

/*
1. 抽象类：Dodge.class，表示对闪避系统的抽象，其中包含一个抽象方法dodgeEnemy()和一个
Dodge本身的变量next，如果当前防御系统不满足条件，则调用next变量执行其中的
dodgeEnemy()方法。下面有一个简单的示例：
最后你需要在主函数中，将四个等级的闪避系统对象依次赋值给上一级闪避系统的next变量。像下
面这样：
2. 具体实现类：DodgeLv1.class, DodgeLv2.class, DodgeLv3.class, DodgeLv4.class，分别表示四个
等级的闪避系统。
public abstract Dodge {
Dodge next; // 下一级闪避系统
abstract void dodgeEnemy(Enemy enemy); // 躲避敌人攻击的抽象方法，在其子类里具
体实现
}
dodgeLv1.setNext(dodgeLv2);
dodgeLv2.setNext(dodgeLv3);
*/
public class DodgeDemo {
    public static void main(String[] args) {
        Enemy enemy = new Enemy();
        enemy.setAttack(50);
        DodgeLv1 dodgeLv1 = new DodgeLv1();
        DodgeLv2 dodgeLv2 = new DodgeLv2();
        DodgeLv3 dodgeLv3 = new DodgeLv3();
        DodgeLv4 dodgeLv4 = new DodgeLv4(Dodge.resistance);
        enemy.atk(dodgeLv4.setNext(dodgeLv3).setNext(dodgeLv2).setNext(dodgeLv1));
        dodgeLv2.setNext(dodgeLv1);
        enemy.atk(dodgeLv2);
        dodgeLv3.setNext(dodgeLv2);
        enemy.atk(dodgeLv3);
        dodgeLv4.setNext(dodgeLv3);
        enemy.atk(dodgeLv4);
    }
}

class DodgeLv1 extends Dodge {
    public DodgeLv1(){}
    public DodgeLv1(int r){}
    @Override
    void dodgeEnemy(Enemy enemy) {
        if (enemy.getAttack() >=(resistance * 3)) {
            System.out.println("王子逃跑！");
        }
    }
}

class DodgeLv2 extends Dodge {
    public DodgeLv2() {
    }
    @Override
    void dodgeEnemy(Enemy enemy) {
        if (enemy.getAttack() >= (resistance * 2)) {
            System.out.println("王子挡下一击，选择逃跑！");
        }
    }
}

class DodgeLv3 extends Dodge {
    public DodgeLv3() {
    }
    @Override
    void dodgeEnemy(Enemy enemy) {
        if (enemy.getAttack() >= resistance) {
            System.out.println("选择躲避本次攻击");
            enemy.attacked();
        }
    }
}

class DodgeLv4 extends Dodge {
    public DodgeLv4(int r) {
    }
    @Override
    void dodgeEnemy(Enemy enemy) {
        if (enemy.getAttack() < resistance) {
            System.out.println("王子选择直接反击！");
            enemy.attacked();
        }
    }
}