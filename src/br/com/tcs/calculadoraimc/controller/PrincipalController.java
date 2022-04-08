package br.com.tcs.calculadoraimc.controller;

import java.awt.TrayIcon.MessageType;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import br.com.tcs.calculadoraimc.model.business.CalculadoraPessoal;
import br.com.tcs.calculadoraimc.model.business.HistoricoBean;
import br.com.tcs.calculadoraimc.model.dao.HistoricoJPARepository;
import br.com.tcs.calculadoraimc.model.models.HistoricoModel;
import br.com.tcs.calculadoraimc.utils.jdbc.DateUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrincipalController implements Initializable {
	@FXML
	private TextField iptAltura;
	@FXML
	private TextField iptPeso;
	@FXML
	private Label lblStatus;
	@FXML
	private Button btnCalcular;

	@FXML
	private Label title;

	@FXML
	private TableView<HistoricoModel> tbHistorico;

	@FXML
	private TableColumn<HistoricoModel, Date> colDataHora;

	@FXML
	private TableColumn<HistoricoModel, String> colStatus;

	private final ObservableList<HistoricoModel> lista = FXCollections.observableArrayList();
	
	private HistoricoBean bean;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		HistoricoJPARepository historicoDAO = new HistoricoJPARepository();
//		HistoricoJDBCRepository historicoDAO = new HistoricoJDBCRepository("imc", "root", "mutkch");
		bean = new HistoricoBean(historicoDAO);
		setTableViewProperties();
		listarHistoricos();
	}

	private void setTableViewProperties() {
		// Inicia a tabela com a lista Obervable
		tbHistorico.setItems(lista);
		// Define qual atributo do objeto da tabela que será mapeado para cada coluna.
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		colDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora"));

		// faz uma formatação no formato de data para ser apresentado a data formatada
		// para o usuário
		colDataHora.setCellFactory(column -> {
			TableCell<HistoricoModel, Date> cell = new TableCell<HistoricoModel, Date>() {
				@Override
				protected void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setText(null);
					} else {
						this.setText(DateUtils.dateToString(item, "dd/MM/yyyy HH:mm:ss"));
					}
				}
			};
			return cell;
		});
		setFieldOnlyNumbers(iptAltura);
		setFieldOnlyNumbers(iptPeso);
	}

	//trata o campo para aceitar apenas números e virgula.
	private void setFieldOnlyNumbers(TextField field) {
		Pattern pattern = Pattern.compile("\\d*|\\d+\\,\\d*");
		TextFormatter<String> formatter = new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
		    return pattern.matcher(change.getControlNewText()).matches() ? change : null;
		});
		field.setTextFormatter(formatter);
	}

	@FXML
	public void calcular(ActionEvent event) {
		try {
			CalculadoraPessoal calculadora = new CalculadoraPessoal();
			String retorno = calculadora.calcularImc(iptAltura.getText(), iptPeso.getText());
			atualizaStatusNoDisplay(retorno);
			inserirHistorico(retorno);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", MessageType.ERROR.ordinal());
		} finally {
			limpar();
		}
	}

	private void atualizaStatusNoDisplay(String retorno) throws InterruptedException {
		Task<?> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Platform.runLater(() -> {
					updateMessage(retorno);
				});
				Thread.sleep(2000);
				Platform.runLater(() -> {
					updateMessage("");
				});
				return null;
			}
		};
		lblStatus.textProperty().bind(task.messageProperty());
		new Thread(task).start();
	}

	private void limpar() {
		iptAltura.setText(null);
		iptPeso.setText(null);
		iptAltura.requestFocus();
	}
	
	private void listarHistoricos() {
		try {
			lista.setAll(bean.listaTodos());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", MessageType.ERROR.ordinal());
		}
	}
	
	private void inserirHistorico(String retorno) throws Exception{
		try {
			HistoricoModel model = new HistoricoModel(retorno);
			bean.salva(model);
			listarHistoricos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", MessageType.ERROR.ordinal());
		}
	}

}
