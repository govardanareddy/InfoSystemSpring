package patterns;

import java.util.regex.Pattern;

public class StudentPattern {
	private Pattern wordPattern = Pattern.compile("([a-zA-Z//-]/?)+");
	private Pattern numberPattern = Pattern.compile("[0-9]{3}");
	private Pattern idPattern = Pattern.compile("[0-9]{8}");
	private Pattern datePattern = Pattern.compile("(([12]?[0-9])||(3[01]))\\.((0[1-9])||(1[012]))\\.[0-9]{4}");
	
	public boolean matcher(String surname, String name, String secondName,	String faculty, String groupNumber, String studentID, String dateOfTransfer){
		if (wordPattern.matcher(surname).matches() && wordPattern.matcher(name).matches() && wordPattern.matcher(secondName).matches() &&
				wordPattern.matcher(faculty).matches() && numberPattern.matcher(groupNumber).matches() && idPattern.matcher(studentID).matches() &&
				datePattern.matcher(dateOfTransfer).matches())
			return true;
		else return false;
	}
	
	public boolean wordMatcher(String word){
		if (wordPattern.matcher(word).matches()) return true;
		else return false;
	}
	public boolean numberMatcher(String number){
		if (numberPattern.matcher(number).matches()) return true;
		else return false;
	}
	public boolean idMatcher(String id){
		if (idPattern.matcher(id).matches()) return true;
		else return false;
	}
	public boolean dateMatcher(String date){
		if (datePattern.matcher(date).matches()) return true;
		else return false;
	}
}
