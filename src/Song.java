public class Song {
    private String title;
    private int durationMinutes;
    private int durationSeconds;
    private String duration;

    public Song() {
    }

    public Song(String title, int durationMinutes, int durationSeconds) {
        if(durationMinutes < 0) {
            durationMinutes = 0;
        }
        if(durationSeconds < 0) {
            durationSeconds = 0;
        }
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.durationSeconds = durationSeconds;
        this.duration = "";
        duration += this.durationMinutes + ":";
        if(this.durationSeconds < 10) {
            duration += "0" + this.durationSeconds;
        } else {
            duration += this.durationSeconds;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public  String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}