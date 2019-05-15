package sample.presentation

interface BaseView {
    fun showError(message: String)
}

abstract class BasePresenter<T : BaseView>(
    private val baseView: T
) {
    open fun onStart(): BasePresenter<T> {
        return this
    }

    open fun onFinish(): BasePresenter<T> {
        return this
    }
}
