package paint.command;

// [COMMAND PATTERN] Interface for all executable commands
// [نمط Command] واجهة لجميع الأوامر القابلة للتنفيذ
public interface Command {
    void execute(); // Do the action / تنفيذ الفعل
    void undo();    // Reverse the action / عكس الفعل
}