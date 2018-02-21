package com.example.part_1;

import com.example.annotations.Complexity;
import com.example.common.StringEventPublisher;
import reactor.util.annotation.Nullable;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.annotations.Complexity.Level.EASY;
import static com.example.annotations.Complexity.Level.HARD;
import static com.example.annotations.Complexity.Level.MEDIUM;

public class Part1CreationTransformationTermination {

    @Complexity(EASY)
    public static Observable<String> justABC() {
        // TODO: return "ABC" using Observable API
        // HINT: rx.Observable.just(T)

        return rx.Observable.just("ABC");

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Observable<String> fromArray(String... args) {
        // TODO: return Observable of input args
        // HINT: rx.Observable.from(T[])

        return rx.Observable.from(args);

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Observable<String> error(Throwable t) {
        // TODO: return error Observable with given Throwable

        return rx.Observable.error(t);

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Observable<Integer> convertNullableValueToObservable(@Nullable Integer nullableElement) {
        // TODO: return empty Observable if element is null otherwise return Observable from that element

        if (nullableElement == null) {
            return rx.Observable.empty();
        }
        return rx.Observable.just(nullableElement);

      //return Optional.of(nullableElement).map(x -> rx.Observable.just(x)).orElseGet(x -> Observable.<Integer>empty());

//        return rx.Observable.;
//        just(nullableElement);

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Observable<String> deferCalculation(Func0<Observable<String>> calculation) {
        // TODO: return deferred Observable
        // HINT: rx.Observable.defer()

        return rx.Observable.defer(calculation);

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Observable<Long> interval(long interval, TimeUnit timeUnit) {
        // TODO: return interval Observable
        return rx.Observable.interval(interval, timeUnit);

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Observable<String> mapToString(Observable<Long> input) {
        // TODO: map to String;
        // HINT: Use String::valueOf or Object::toString as mapping function

        return input.map(String::valueOf);

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(EASY)
    public static Observable<String> findAllWordsWithPrefixABC(Observable<String> input) {
        // TODO: filter strings
        // HINT: use String#startsWith

        return input.filter(s -> s.startsWith("ABC"));

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(MEDIUM)
    public static Observable<String> fromFutureInIOScheduler(Future<String> future) {
        // TODO: return Observable from future scheduled on IO scheduler
        // HINT: rx.Observable.from(java.util.concurrent.Future<? extends T>, rx.Scheduler)
        // HINT: for IO Scheduler take a look at rx.schedulers.Schedulers.*

        return rx.Observable.from(future, Schedulers.io());

//        throw new RuntimeException("Not implemented");
    }

    @Complexity(MEDIUM)
    public static void iterateNTimes(int times, AtomicInteger counter) {
        // TODO: refactor using Observable#range and Observable#subscribe or Observable#doOnNext
//        throw new RuntimeException("Not implemented");

        rx.Observable.range(0, times).subscribe(ignore -> counter.incrementAndGet());
    }

    @Complexity(HARD)
    public static Observable<String> adaptToObservable(StringEventPublisher eventPublisher) {
        // TODO: when subscriber of the returned Observable<String> has subscribed,
        //       they should receive data emitted from the StringEventPublisher

        // NOTE: StringEventPublisher is a simple data source to which we may subscribe in the plain java in the next way:
        //
        //       eventPublisher.registerEventListener(new Consumer<String>() {
        //           @Override
        //           public void accept(String s) {
        //               System.out.println(s);
        //           }
        //       });

        // NOTE: When you use Observable.unsafeCreate the parameter is also function which looks like next:
        //
        //        Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
        //            @Override
        //            public void call(Subscriber<? super String> subscriber) {
        //
        //            }
        //        });

        // NOTE: As we learned earlier, Subscriber has method onNext which should be called every time
        //       eventPublisher.registerEventListener(new Consumer<String>()... emits new value

        // TODO: adapt to Observable; consider Observable#unsafeCreate
        // HINT: combine eventPublisher.registerEventListener( with OnSubscribe::onNext )

//        throw new RuntimeException("Not implemented");
        

        return rx.Observable.unsafeCreate(subscriber -> eventPublisher.registerEventListener(subscriber::onNext)) ;
        
    }

    @Complexity(MEDIUM)
    public static Observable<Character> flatMapWordsToCharacters(Observable<String> input) {
        // TODO: flat map strings to character
        // HINT: to split string on characters use string.split("")
        // HINT: remind how to wrap array to Observable
        // HINT: consider string.charAt(0) for mapping one letter string to character

//        throw new RuntimeException("Not implemented");

        return input.flatMap(s -> rx.Observable.from(s.split(""))).map(s -> s.charAt(0));

    }


}
