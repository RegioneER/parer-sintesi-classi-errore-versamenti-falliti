package it.eng.parer.classierrore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.quarkus.test.junit.QuarkusTestProfile;

public class Profiles {

    public static class Lab implements QuarkusTestProfile {
	@Override
	public Set<String> tags() {
	    return new HashSet<>(Arrays.asList("lab"));
	}
    }

    public static class Core implements QuarkusTestProfile {
	@Override
	public Set<String> tags() {
	    return new HashSet<>(Arrays.asList("unit"));
	}

	@Override
	public String getConfigProfile() {
	    return "test";
	}
    }

    public static class EndToEnd implements QuarkusTestProfile {
	@Override
	public Set<String> tags() {
	    return new HashSet<>(Arrays.asList("e2e"));
	}

	@Override
	public String getConfigProfile() {
	    return "test";
	}
    }
}
