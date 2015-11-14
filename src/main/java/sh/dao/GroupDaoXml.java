package sh.dao;

import org.xml.sax.SAXException;
import sh.exception.BadXmlFileException;
import sh.exception.NothingToDeleteException;
import sh.groups.Group;
import sh.students.Student;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class GroupDaoXml implements GroupDao {

    private StudDao sdao;

    public StudDao getStudDao() {
        return sdao;
    }

    public void setStudDao(StudDao sdao) {
        this.sdao = sdao;
    }

    public Collection<Group> readAll() throws BadXmlFileException, SAXException, IOException, ParserConfigurationException {
        Collection<Group> groups = new ArrayList<Group>();
        Collection<Student> students = sdao.readAll();
        boolean b = true;
        for (Student st : students) {
            for (Group gr : groups)
                if (gr.getFaculty().equals(st.getFaculty()) && gr.getGroup().equals(st.getGroupNumber()))
                    b = false;

            if (b) groups.add(new Group(st.getFaculty(), st.getGroupNumber()));
            b = true;
        }
        return groups;
    }

    public void delete(String number, String faculty) throws IOException, BadXmlFileException, SAXException, ParserConfigurationException, NothingToDeleteException {
        Collection<Student> students = sdao.readAll();
        for (Student stud : students)
            if (stud.getGroupNumber().equals(number) && stud.getFaculty().equals(faculty))
                sdao.delete(stud);
    }

    public void update(String faculty, String group, String renamedgroup) throws BadXmlFileException, SAXException, ParserConfigurationException, IOException {
        Collection<Student> students = sdao.readAll();
        int index = 0;
        for (Student st : students) {
            if (faculty.equals(st.getFaculty()) && group.equals(st.getGroupNumber()))
                sdao.update(new Student(st.getSurname(), st.getName(), st.getSecondName(), st.getFaculty(), renamedgroup, st.getStudentID(), st.getDateOfTransfer()), index);
            index++;
        }
    }
}
