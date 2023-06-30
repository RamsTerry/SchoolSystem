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
import sars.gov.za.schoolManagement.domain.Subject;
import sars.gov.za.schoolManagement.service.SubjectServiceLocal;

/**
 *
 * @author S2028398
 */
@ManagedBean
@ViewScoped
public class SubjectBean extends BaseBean<Subject> {

    @Autowired
    private SubjectServiceLocal subjectService;

    @PostConstruct
    public void init() {
        reset().setList(true);
        setPanelTitleName("List Of Subject");
        addCollections(subjectService.listAll());
    }

    public void addOrUpdate(Subject subject) {
        reset().setAdd(true);
        if (subject != null) {
            setPanelTitleName("Update Subject");
            subject.setUpdatedBy(getActiveUser().getIdentifier());
            subject.setUpdatedDate(new Date());
        } else {
            setPanelTitleName("Add Subject");
            subject = new Subject();
            subject.setCreatedBy(getActiveUser().getIdentifier());
            subject.setCreatedDate(new Date());
            addCollection(subject);
        }
        addEntity(subject);
    }

    public void save(Subject subject) {
        if (subject.getId() != null) {
            subjectService.update(subject);
            addInformationMessage("Subject was successfully updated.");
        } else {
            subjectService.save(subject);
            addInformationMessage("Subject successfully created.");
        }
        reset().setList(true);
        setPanelTitleName("List of Subject");
    }

    public void cancel(Subject subject) {
        if (subject.getId() == null && getSubjects().contains(subject)) {
            remove(subject);
        }
        reset().setList(true);
        setPanelTitleName("List of Subject");
    }

    public void delete(Subject subject) {
        subjectService.deleteById(subject.getId());
        remove(subject);
        addInformationMessage("Subject was successfully deleted");
        reset().setList(true);
        setPanelTitleName("List of Subject");
    }

    public List<Subject> getSubjects() {
        return this.getCollections();
    }
}
