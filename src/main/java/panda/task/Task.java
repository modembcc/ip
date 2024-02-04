package panda.task;

public class Task {
    private final String desc;
    private boolean isDone;
    
    /**
     * Constructs a new Task with the given description.
     * 
     * @param desc the description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the current status of the task.
     * 
     * @return the current status of the task.
     */
    private String curStatus() {
        return "[" + (isDone ? 'X' : ' ') + "]";
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return the string representation of the task.
     */
    public String toString() {
        return curStatus() + ' ' + desc;
    }

    /**
     * Returns the string representation of the task suitable for saving to a file.
     * 
     * @return the string representation of the task.
     */
    public String saveString() {
        return (isDone ? "1" : "0") + " | " + desc;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }
         
        Task c = (Task) o;

        return desc.equals(c.desc) && isDone == c.isDone;
    }
}
