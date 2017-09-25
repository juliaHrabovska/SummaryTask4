package ua.nure.hrabovska.SummaryTask4.database.bean;

import ua.nure.hrabovska.SummaryTask4.database.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Provide records for virtual table:
 * <pre>
 * |cathedra.name|cathedra.level_of_training|cathedra.type_of_training|department.name|cathedra.licensed_volume|
 *      |cathedra.licensed_volume_budget|cathedra.licensed_volume_contract|cathedra.requirements|cathedra.statement|
 *      |cathedra.recommended|cathedra.enlisted|
 * </pre>
 *
 * @author Y. Hrabovska
 */
public class CathedraBean extends Entity implements Serializable {

    private String name;

    private String level_of_training;

    private String type_of_training;

    private String department_name;

    private long department_id;

    private int licensed_volume_budget;

    private int licensed_volume_contract;

    private int licensed_volume;

    private List<String> requirements;

    private int statement;

    private int recommended;

    private int enlisted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel_of_training() {
        return level_of_training;
    }

    public void setLevel_of_training(String level_of_training) {
        this.level_of_training = level_of_training;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    public int getLicensed_volume_budget() {
        return licensed_volume_budget;
    }

    public void setLicensed_volume_budget(int licensed_volume_budget) {
        this.licensed_volume_budget = licensed_volume_budget;
    }

    public int getLicensed_volume_contract() {
        return licensed_volume_contract;
    }

    public void setLicensed_volume_contract(int licensed_volume_contract) {
        this.licensed_volume_contract = licensed_volume_contract;
    }

    public int getLicensed_volume() {
        return licensed_volume;
    }

    public void setLicensed_volume(int licensed_volume_budget, int licensed_volume_contract) {
        this.licensed_volume = licensed_volume_budget + licensed_volume_contract;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public String getType_of_training() {
        return type_of_training;
    }

    public void setType_of_training(String type_of_training) {
        this.type_of_training = type_of_training;
    }

    public int getStatement() {
        return statement;
    }

    public void setStatement(int statement) {
        this.statement = statement;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public int getEnlisted() {
        return enlisted;
    }

    public void setEnlisted(int enlisted) {
        this.enlisted = enlisted;
    }

    @Override
    public String toString() {
        return "Cathedra [id=" + getId()
                + ", name=" + name
                + ", licensed_volume=" + licensed_volume
                + ", licensed_volume_budget=" + licensed_volume_budget
                + ", licensed_volume_contract=" + licensed_volume_contract
                + ", type_of_training=" + type_of_training
                + ", level_of_training=" + level_of_training
                + ", department_name=" + department_name
                + ", department_id=" + department_id
                + ", statement=" + statement
                + ", recommended=" + recommended
                + ", enlisted=" + enlisted
                + ", requirements=" + requirements
                + "]";
    }
}
