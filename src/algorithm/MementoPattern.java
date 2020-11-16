package algorithm;

import java.util.Stack;

/**
 * @author lx
 */
public class MementoPattern {
    public static void main(String[] args) {
        History history = new History();
        Document document = new Document();

        //每次被修改时进行快照,同时快照对象入栈
        document.change("1");
        history.add(document.save());
        //恢复快照
        document.resume(history.getLastVersion());
    }
}


interface Memento {
}

class History {
    Stack<Backup> stack = new Stack<>();

    public void add(Backup backup) {
        stack.add(backup);
    }

    public Backup getLastVersion() {
        return stack.pop();
    }
}

class Document {
    //需要备份的内容
    private String content;

    public Backup save() {
        return new Backup(content);
    }

    public void resume(Backup backup) {
        content = backup.content;
    }

    public void change(String content) {
        this.content = content;
    }
}

class Backup implements Memento {

    String content;

    public Backup(String content) {
        this.content = content;
    }
}