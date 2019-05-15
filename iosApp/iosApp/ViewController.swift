import UIKit
import app

class ViewController: UIViewController, MainView {

    lazy var mainPresenter: MainPresenter = MainPresenter(mainView: self)

    override func viewDidLoad() {
        super.viewDidLoad()
        mainPresenter.onStart()
    }

    override func viewWillDisappear(_ animated: Bool) {
        mainPresenter.onFinish()
    }

    @IBOutlet weak var button: UIButton!
    @IBOutlet weak var input: UITextField!
    @IBOutlet weak var text: UITextView!
    
    @IBAction func onClickButton(_ sender: UIButton) {
        mainPresenter.searchWeather(location: self.input.text ?? "")
    }

    func setButtonText(text: String) {
        self.button.setTitle(text, for: .normal)
    }

    func setTextContent(text: String) {
        self.text.text = text
    }

    func showError(message: String) {
        let alert = UIAlertController(title: "Error", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: NSLocalizedString("OK", comment: "Default action"), style: .default, handler: { _ in
            NSLog("The \"OK\" alert occured.")
        }))
        self.present(alert, animated: true, completion: nil)
    }
}
