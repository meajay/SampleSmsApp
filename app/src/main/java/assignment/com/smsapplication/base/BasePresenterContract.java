package assignment.com.smsapplication.base;

public interface BasePresenterContract<V extends BaseViewContract>{
    void onAttach(V view);

    void onDetach();

    boolean isViewAttached();

    V getView();
}
