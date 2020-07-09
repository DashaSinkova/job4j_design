package ru.job4j.collection;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Analize {
    private int added = 0;
    private int changed = 0;
    private int deleted = 0;

    /*метод должен возвращать статистику об изменении коллекции.
Изменённым считается объект в котором изменилось имя. а id осталось прежним.
Сколько добавлено новых пользователей.
Сколько удалено пользователей.*/
    public Info diff(List<User> previous, List<User> current) {
        Iterator<User> prevIt = previous.iterator();
        Iterator<User> curIt = current.iterator();
        while (prevIt.hasNext() & curIt.hasNext()) {
            User userPrev = prevIt.next();
            User userCur = curIt.next();
            checkAdded(userCur, previous);
            checkChanged(userPrev, current);
        }

        while (prevIt.hasNext()) {
            User userPrev = prevIt.next();
            checkChanged(userPrev, current);
        }
        while (curIt.hasNext()) {
            User userCur = curIt.next();
            checkAdded(userCur, previous);
        }
        return new Info(added, changed, deleted);
    }

    private void checkChanged(User userPrev, List<User> current) {
        if (!current.contains(userPrev)) {
            deleted++;
        } else {
            int i = current.indexOf(userPrev);
            User user = current.get(i);
            if (!user.getName().equals(userPrev.getName())) {
                changed++;
            }
        }
    }

    private void checkAdded(User userCur, List<User> previous) {
        if (!previous.contains(userCur)) {
            added++;
        }
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
