package CIS350TermProject;



import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class TaskList extends AbstractTableModel {
    protected ArrayList<Task> list;
    private int size;
    protected static final int CLASSNAME = 1;
    protected static final int DUEDATE = 2;
    protected static final int PRIORITY = 3;
    protected int sorting = Agenda.sorting;


    protected String[] columnTaskList = {"Assignment Name", "Class Name", "Due Date", "Priority" };

    @Override
    public String getColumnName(int col){
        return columnTaskList[col];
    }

    @Override
    public int getRowCount(){
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnTaskList.length;
    }

    @Override
    public Object getValueAt(int row, int col){
        return taskScreen(row, col);
    }

    public Object taskScreen(int row, int col){
        switch (col) {
            case 0:
                return (list.get(row).getAssignmentName());

            case 1:
                return (list.get(row).getClassName());

            case 2:
                return (list.get(row).getDueDate());

            case 3:
                return (list.get(row).getPriority());

            default:
                throw new RuntimeException("JTable row, col is out of range" + row + " " + col);
        }

    }

    public void Update(){
        switch (sorting){
            case CLASSNAME:
                list = (ArrayList<Task>) list.stream().filter(n -> n.getAssignmentName() != null). collect(Collectors.toList());
                Collections.sort(list, ((a1, a2) -> a1.getClassName().compareTo(a2.getClassName())));
                break;
            case DUEDATE:
                list = (ArrayList<Task>) list.stream().filter(n -> n.getAssignmentName() != null). collect(Collectors.toList());
                Collections.sort(list, ((a1, a2) -> a1.getDueDate().compareTo(a2.getDueDate())));
                break;
            case PRIORITY:
                list = (ArrayList<Task>) list.stream().filter(n -> n.getAssignmentName() != null). collect(Collectors.toList());
                Collections.sort(list, ((a1, a2) -> Integer.compare(a1.getPriority(), a2.getPriority())));
                break;
        }
        fireTableDataChanged();

    }

    public void setSorting(int selected){
        sorting = selected;
        Update();
    }


    public Task get(int i){
        return list.get(i);
    }

    public TaskList(){
        super();
        this.list  = new ArrayList<Task>();
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task){
        list.add(task);
        size++;
        fireTableDataChanged();
    }

    public void removeTask(Task task){
        list.remove(task);
        size--;
        fireTableDataChanged();
    }

    public static void main(String[] arg){
        TaskList list = new TaskList();

        Task x = new Task("HW5", "Cis263", "20201510", 1);
        Task y = new Task("HW3", "Cis350", "20201510", 5);
        Task z = new Task("Test", "Cis241", "20201510", 10);
        Task a = new Task("Quiz", "Mth325", "20201510", 6);
        list.addTask(x);
        list.addTask(y);
        list.addTask(z);
        list.addTask(a);

        for(int i = 0; i < list.size; i++){
            System.out.println(list.getList().get(i).toString());
        }

        list.removeTask(z);

        for(int i = 0; i < list.size; i++){
            System.out.println(list.getList().get(i).toString());
        }

    }

}
