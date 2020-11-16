package other;

public class CommandPattern {
    public static void main(String[] args) {
        SaveButton saveButton = new SaveButton();
        Textbox textbox = new Textbox();

        //将页面对象与命令绑定,命令的构造方法中传入了页面元素textbox
        saveButton.bindCommand(new PrintCommand(textbox));

        textbox.setContext("aa");
        //页面对象调用命令对象      命令对象调用业务逻辑,处理页面元素
        saveButton.doPrint();

        textbox.setContext("bb");
        saveButton.doPrint();
    }
}

/**
 * UI层,保存按钮
 */
class SaveButton {
    private Command command;

    /**
     *  传入命令对象,完成按钮与命令的绑定,实现了UI和逻辑的分离
     */
    public void bindCommand(Command command) {
        this.command = command;
    }

    public void doPrint() {
        if (null != command) {
            command.execute();
        }
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


