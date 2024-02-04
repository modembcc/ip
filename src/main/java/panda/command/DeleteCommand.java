package panda.command;
import panda.exception.PandaException;
import panda.exception.OutOfBoundsException;
import panda.component.*;
public class DeleteCommand extends Command {
    private int idx;

    /**
     * Constructs a new DeleteCommand.
     * 
     * @param idx the index of the task to delete.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Deletes the task at the given index from the TaskList.
     * 
     * @param tlist the TaskList from which the task is deleted.
     * @throws PandaException if an error occurs during deletion.
     */
    public void execute(TaskList tlist) throws PandaException{
        if(idx >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        tlist.remove(idx);
        return;
    }

    /**
     * Deletes the task at the given index from the TaskList, updates the UI, and saves changes to the cache file.
     * 
     * @param tlist the TaskList from which the task is deleted.
     * @param ui the UI to update after deletion.
     * @param cacheFile the cache file to save changes to.
     * @throws PandaException if an error occurs during deletion.
     */
    public void execute(TaskList tlist, Ui ui, Storage cacheFile) throws PandaException {
        if(idx >= tlist.size()) {
            throw new OutOfBoundsException();
        }
        String tString = tlist.taskString(idx);
        tlist.remove(idx);
        cacheFile.save(tlist);
        ui.showReply("OK, I've deleted this task:\n  " + tString + "\nNow you have " + tlist.size() + " tasks in the list.");
        return;
    }

    /**
     * Checks if the command is an exit command.
     * 
     * @return always false, as this command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof DeleteCommand)) {
            return false;
        }
         
        DeleteCommand c = (DeleteCommand) o;

        return idx == c.idx;
    }
}
