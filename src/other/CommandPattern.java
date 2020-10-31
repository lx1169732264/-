package other;

/**
 * @author lx
 */
public class CommandPattern {
    public static void main(String[] args) {
        SaveButton saveButton = new SaveButton();
        Textbox textbox = new Textbox();

        saveButton.bindCommand(new PrintCommand(textbox));

        textbox.setContext("aa");
        saveButton.doPrint();

        textbox.setContext("bb");
        saveButton.doPrint();
    }
}

/**
 * UI层,保存按钮
 * 构造方法传入命令对象,完成按钮与命令的绑定
 * 实现了UI和逻辑的分离
 */
class SaveButton {
    private Command command;

    public void bindCommand(Command command) {
        this.command = command;
    }

    public void doPrint() {
        if (null == command) {
            throw new RuntimeException("初始化失败");
        }
        command.execute();
    }
}

/**
 * UI层,按钮点击后显示的文本
 */
class Textbox {
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}


/**
 * 逻辑层  打印服务
 */
class PrintService {
    public void print(String text) {
        System.out.print(text);
    }
}

/**
 * 命令接口
 */
interface Command {
    void execute();
}

/**
 * 具体命令
 */
class PrintCommand implements Command {

    private final PrintService printService = new PrintService();
    private Textbox textbox;

    public PrintCommand(Textbox textbox) {
        this.textbox = textbox;
    }

    @Override
    public void execute() {
        printService.print(textbox.getContext());
    }
}


