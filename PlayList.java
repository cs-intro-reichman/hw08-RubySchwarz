class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getSize() {
        return size;
    }

    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }

    public boolean add(Track track) {
        if (size < maxSize) {
            tracks[size] = track;
            size++;
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(tracks[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public void removeLast() {
        if (size > 0) {
            size--;
        }
    }

    public int totalDuration() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += tracks[i].getDuration();
        }
        return total;
    }

    public int indexOf(String title) {
        for (int i = 0; i < size; i++) {
            if (tracks[i].getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    public boolean add(int i, Track track) {
        if (i < 0 || i > size || size >= maxSize) {
            return false;
        }
        for (int j = size; j > i; j--) {
            tracks[j] = tracks[j - 1];
        }
        tracks[i] = track;
        size++;
        return true;
    }

    public void remove(int i) {
        if (i < 0 || i >= size) {
            return;
        }
        for (int j = i; j < size - 1; j++) {
            tracks[j] = tracks[j + 1];
        }
        size--;
    }

    public void remove(String title) {
        int index = indexOf(title);
        if (index != -1) {
            remove(index);
        }
    }

    public void removeFirst() {
        if (size > 0) {
            remove(0);
        }
    }

    public void add(PlayList other) {
        for (int i = 0; i < other.size && size < maxSize; i++) {
            add(other.tracks[i]);
        }
    }

    private int minIndex(int start) {
        if (start < 0 || start >= size) {
            return -1;
        }
        int minIndex = start;
        for (int i = start + 1; i < size; i++) {
            if (tracks[i].getDuration() < tracks[minIndex].getDuration()) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public String titleOfShortestTrack() {
        int minIndex = minIndex(0);
        if (minIndex != -1) {
            return tracks[minIndex].getTitle();
        }
        return null;
    }

    public void sortedInPlace() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = minIndex(i);
            if (minIndex != i) {
                Track temp = tracks[i];
                tracks[i] = tracks[minIndex];
                tracks[minIndex] = temp;
            }
        }
    }
}
