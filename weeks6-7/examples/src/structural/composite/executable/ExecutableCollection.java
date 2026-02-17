package structural.composite.executable;

import java.util.ArrayList;
import java.util.Collection;

public class ExecutableCollection implements Executable { // Composite
    protected Collection<Executable> executables = new ArrayList<>();

    public void addExecutable(Executable executable) {
        executables.add(executable);
    }

    public void removeExecutable(Executable executable) {
        executables.remove(executable);
    }

    @Override
    public void execute() {
        for (Executable executable : executables)
            executable.execute();
    }
}