package test.com.courserating;

import android.os.Parcel;
import android.os.Parcelable;

public class TeacherRating implements Parcelable {

    private String initials;
    private String subject;
    private String relevans;
    private String performance;
    private String preparation;

    public TeacherRating() {
    }

    public TeacherRating(String initials, String subject, String relevans, String performance, String preparation) {
        this.initials = initials;
        this.subject = subject;
        this.relevans = relevans;
        this.performance = performance;
        this.preparation = preparation;
    }

    protected TeacherRating(Parcel in) {
        initials = in.readString();
        subject = in.readString();
        relevans = in.readString();
        performance = in.readString();
        preparation = in.readString();
    }

    public static final Creator<TeacherRating> CREATOR = new Creator<TeacherRating>() {
        @Override
        public TeacherRating createFromParcel(Parcel in) {
            return new TeacherRating(in);
        }

        @Override
        public TeacherRating[] newArray(int size) {
            return new TeacherRating[size];
        }
    };

    public String getTeacherName() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectRelevans() {
        return relevans;
    }

    public void setRelevans(String relevans) {
        this.relevans = relevans;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    @Override
    public String toString() {
        return "TeacherRating{" +
                "initials='" + initials + '\'' +
                ", subject='" + subject + '\'' +
                ", relevans='" + relevans + '\'' +
                ", performance='" + performance + '\'' +
                ", preparation='" + preparation + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(initials);
        parcel.writeString(subject);
        parcel.writeString(relevans);
        parcel.writeString(performance);
        parcel.writeString(preparation);
    }
}
