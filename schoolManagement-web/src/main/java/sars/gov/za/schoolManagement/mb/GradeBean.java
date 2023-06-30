/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sars.gov.za.schoolManagement.mb;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import sars.gov.za.schoolManagement.domain.Grade;
import sars.gov.za.schoolManagement.domain.School;
import sars.gov.za.schoolManagement.service.GradeServiceLocal;
import sars.gov.za.schoolManagement.service.SchoolServiceLocal;

/**
 *
 * @author S2028398
 */
@ManagedBean
@ViewScoped
public class GradeBean extends BaseBean<Grade> {

    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private SchoolServiceLocal schoolService;
    
    private School school;

    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("List Of Grade");
        addCollections(gradeService.listAll());
        school = schoolService.findBySchoolName("SARS Management");
    }

    public void addOrUpdate(Grade grade) {
        reset().setAdd(true);
        if (grade != null) {
            setPanelTitleName("Update Grdae");
            grade.setUpdatedBy(getActiveUser().getIdentifier());
            grade.setUpdatedDate(new Date());
        } else {
            setPanelTitleName("Add Grade");
            grade = new Grade();
            grade.setCreatedBy(getActiveUser().getIdentifier());
            grade.setCreatedDate(new Date());
            grade.setSchool(school);
            addCollection(grade);
        }
        addEntity(grade);
    }

    public void save(Grade grade) {
        if (grade.getId() != null) {
            gradeService.update(grade);
            addInformationMessage("Grade was successfully updated.");
        } else {
            gradeService.save(grade);
            addInformationMessage("Grade successfully created.");
        }
        reset().setList(true);
        setPanelTitleName("List of Grade");
    }

    public void cancel(Grade grade) {
        if (grade.getId() == null && getGrades().contains(grade)) {
            remove(grade);
        }
        reset().setList(true);
        setPanelTitleName("List of Grade");
    }

    public void delete(Grade grade) {
        gradeService.deleteById(grade.getId());
        remove(grade);
        addInformationMessage("Grade was successfully deleted");
        reset().setList(true);
        setPanelTitleName("List of Grade");
    }

    public List<Grade> getGrades() {
        return this.getCollections();
    }  
    
}
