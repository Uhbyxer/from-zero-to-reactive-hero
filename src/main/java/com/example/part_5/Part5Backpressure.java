package com.example.part_5;

import com.example.annotations.Complexity;
import com.example.common.StringEventPublisher;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxExtensionsKt;
import reactor.core.publisher.FluxSink;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

import static com.example.annotations.Complexity.Level.EASY;
import static com.example.annotations.Complexity.Level.MEDIUM;

public class Part5Backpressure {

    @Complexity(EASY)
    public static Flux<String> dropElementsOnBackpressure(Flux<String> upstream) {
        // TODO: apply backpressure to drop elements on downstream overwhelming
        // HINT: Flux#onBackpressureDrop

        return upstream.onBackpressureDrop();
    }

    @Complexity(MEDIUM)
    public static Flux<List<Long>> backpressureByBatching(Flux<Long> upstream) {
        // TODO: decrease emission rate by buffering elements during the second
        // HINT: Flux#window(Duration) + .flatMap( .collectList ) or MORE simply Flux#buffer(Duration)

        return upstream.buffer(Duration.ofSeconds(1));
    }

    @Complexity(MEDIUM)
    public static Publisher<String> handleBackpressureWithBuffering(StringEventPublisher stringEventPublisher) {
        // TODO: adapt non-Reactor api and apply backpressure strategy
        // HINT: Flux.create or Flux.push


        return Flux.create(stringFluxSink -> stringEventPublisher.registerEventListener(s -> stringFluxSink.next(s)));

    }
}
