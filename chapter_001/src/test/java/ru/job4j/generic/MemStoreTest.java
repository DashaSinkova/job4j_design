package ru.job4j.generic;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class MemStoreTest {

    @Test
    public void whenAddElements() {
        MemStore<User> userStore = new MemStore<>();
        userStore.add(new User("505"));
        assertThat(userStore.findById("505").getId(), is("505"));
    }
    @Test
    public void whenDeleteElements() {
        MemStore<User> userStore = new MemStore<>();
        userStore.add(new User("505"));
        userStore.add(new User("10"));
        userStore.add(new User("Dasha"));
        assertThat(userStore.delete("Dasha"), is(true));
    }
    @Test
    public void whenReplaceElement() {
        MemStore<User> userStore = new MemStore<>();
        userStore.add(new User("Dasha"));
        assertThat(userStore.replace("Dasha", new User("Kate")), is(true));
    }
    @Test
    public void whenFindById() {
        MemStore<User> userStore = new MemStore<>();
        userStore.add(new User("Dasha"));
        assertThat(userStore.findById("Dasha").getId(), is("Dasha"));
    }
}
