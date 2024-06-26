package tripleo.elijah_remnant.rosetta;

import org.apache.commons.lang3.tuple.Pair;
import org.jdeferred2.DoneCallback;
import tripleo.elijah_fluffy.util.*;
import tripleo.elijah.lang.OS_Module;
import tripleo.elijah.stages.deduce.DeducePhase;
import tripleo.elijah.stages.deduce.DeduceTypes2;

import java.util.ArrayList;
import java.util.List;

public class FakeRosetta3 {
	private static class FakeRosetta3$ {
		private static final List<Pair<DoneCallback<?>, Boolean>> called = new ArrayList<>(); // TODO prol doesnt need to be pcoll?

		private static final FakeRosetta3 INSTANCE = new FakeRosetta3();
	}

	@SuppressWarnings("UnusedReturnValue")
	public static Eventual<DeduceTypes2> mkDeduceTypes2(final OS_Module mod,
	                                                    final DeducePhase phase,
	                                                    final DoneCallback<? super DeduceTypes2> cb) {
		FakeRosetta3$.called.add(Pair.of(cb, false));

		final DeduceTypes2 d = new DeduceTypes2(mod, phase);
		final Eventual<DeduceTypes2> e = new Eventual<>();
		e.resolve(d);
		e.then(new DoneCallback<DeduceTypes2>() {
			@Override
			public void onDone(final DeduceTypes2 result) {
				boolean f  = false;

				for (Pair<DoneCallback<?>, Boolean> p : FakeRosetta3$.called) {
					if (p.getLeft() == result) {
						p.setValue(true);
						f = true;
					}
				}

				if (!f) {
					throw new AssertionError("Internal Logic Error");
				}

				cb.onDone(result);
			}
		});
		return e;
	}

	public static void ensure_all() {
		for (var p : FakeRosetta3$.called) {
			if (!p.getRight()) {
				System.err.println("~~ Didn't call: "+p.getLeft());
			}
		}
	}
}
