package com.wipro.demo.weather;


import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @BeforeClass
    public static void setUpClass() {

        //        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });

//        RxJavaPlugins.setIoSchedulerHandler(__ -> Schedulers.trampoline());

    }

        @Before
    public void setUp() {

    }



}