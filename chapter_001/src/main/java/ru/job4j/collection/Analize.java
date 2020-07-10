package ru.job4j.collection;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        int added = 0;
        int changed = 0;
        Map<Integer, String> prevMap = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User user : current) {
            if (!prevMap.containsKey(user.id)) {
                added++;
            } else {
                if (!user.getName().equals(prevMap.get(user.id))) {
                    changed++;
                }
            }
        }
        int deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;

        }

        public String getName() {
            return name;
        }
    public int getId() {
        return id;
    }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
}

public class Info {
    private int added;
    private int changed;
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    public int getAdded() {
        return added;
    }

    public int getChanged() {
        return changed;
    }

    public int getDeleted() {
        return deleted;
    }
}
}
