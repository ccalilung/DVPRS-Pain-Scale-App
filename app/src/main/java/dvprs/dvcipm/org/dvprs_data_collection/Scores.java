package dvprs.dvcipm.org.dvprs_data_collection;

/**
 * Created by root on 8/26/16.
 */
public class Scores {
    private int _id;
    private int _question1;
    private int _question2;
    private int _question3;
    private int _question4;
    private int _question5;

    public Scores() {

    }

    public Scores(int question1, int question2, int question3, int question4, int question5) {
        this._question1 = question1;
        this._question2 = question2;
        this._question3 = question3;
        this._question4 = question4;
        this._question5 = question5;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_question1(int _question1) {
        this._question1 = _question1;
    }

    public void set_question2(int _question2) {
        this._question2 = _question2;
    }

    public void set_question3(int _question3) {
        this._question3 = _question3;
    }

    public void set_question4(int _question4) {
        this._question4 = _question4;
    }
    
    public void set_question5(int _question5) {
        this._question5 = _question5;
    }

    public int get_id() {
        return _id;
    }

    public int get_question1() {
        return _question1;
    }

    public int get_question2() {
        return _question2;
    }

    public int get_question3() {
        return _question3;
    }

    public int get_question4() {
        return _question4;
    }

    public int get_question5() {
        return _question5;
    }
}
