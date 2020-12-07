package design_pattern.facade;

/**
 * @Description 外观模式
 * 应用实例：
 * 1、去医院看病，可能要去挂号、门诊、划价、取药，让患者或患者家属觉得很复杂，如果有提供接待人员，只让接待人员来处理，就很方便。
 * 2、JAVA 的三层开发模式。
 * <p>
 * 使用场景：
 * 1、为复杂的模块或子系统提供外界访问的模块。
 * 2、子系统相对独立。 3、预防低水平人员带来的风险。
 * <p>
 * 注意事项：在层次化结构中，可以使用外观模式定义系统中每一层的入口。
 * @Author zhanzhan
 * @Date 2020/10/12 18:46
 */
public class FacadePattern {

    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}

/**
 * 步骤 1：创建一个接口
 */
interface Shape {
    void draw();
}

/**
 * 步骤 2：创建实现接口的实体类。
 */
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}

/**
 * 步骤 3：创建一个外观类。
 */
class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
