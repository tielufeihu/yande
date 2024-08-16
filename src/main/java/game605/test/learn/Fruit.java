package game605.test.learn;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Fruit
 * @description TODO
 * @since 2024/8/15 17:40
 */
// 定义水果的父类
public class Fruit {
    public String name;

    public Fruit(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("This is a " + name + ".");
    }

    // 定义测试代码
    public static void main(String[] args) {
        Fruit apple = new Apple("apple");
        apple.show();

        Fruit banana = new Banana("banana");
        banana.show();
    }

}

// 定义苹果类，继承自水果类
class Apple extends Fruit {
    public Apple(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println("This is a red " + name + ".");
    }
}

// 定义香蕉类，继承自水果类
class Banana extends Fruit {
    public Banana(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println("This is a yellow " + name + ".");
    }
}
