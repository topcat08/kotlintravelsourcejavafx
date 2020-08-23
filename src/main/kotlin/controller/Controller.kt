/*
 * Copyright 2020 Spineci Cosmin-Mugurel
 * Address: EU, Romania, Buzau, Street Unirii, block 6B, 2nd floor, 5th apartment
 * email: cosmin.mugurel.23@outlook.com	
 * Authorised to copy or do whatever you want with the code.
*/

package controller


//import javafx.scene.input.ScrollEvent


//import javafx.collections.FXCollections;
//import javafx.fxml.FXML;
import javafx.collections.FXCollections
import javafx.collections.transformation.SortedList
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import model.SomeProperty
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction


class Controller {
   // List Tab Controls
   @FXML lateinit var itemListView: ListView<String>
   @FXML lateinit var newItemField: TextField
   // Table Tab Controls
   @FXML lateinit var tableView: TableView<SomeProperty>
   @FXML lateinit var propertyColumn: TableColumn<SomeProperty, String>
   @FXML lateinit var valueColumn: TableColumn<SomeProperty, String>
// Field Tab Controls
   @FXML lateinit var schemeCombo: ComboBox<String>
   @FXML lateinit var hostField: TextField
   @FXML lateinit var portField: TextField
   @FXML lateinit var generateUrlButton: Button
   @FXML lateinit var urlLabel: Label
  //			CATEGORIES VIEW
  @FXML lateinit var categoryView: TableView<SomeProperty>
  @FXML lateinit var category: TableColumn<SomeProperty, String>
  //keyword table
 @FXML lateinit var filter: TextField
    @FXML lateinit var categoryFilter: TextField

val keysModel = FXCollections.observableArrayList<SomeProperty>()
   val listModel = FXCollections.observableArrayList<String>()
   val tableModel = FXCollections.observableArrayList<SomeProperty>()
   val categoryListModel = FXCollections.observableArrayList<SomeProperty>()

//d?ata class Statistica(val id: Int)
  /**
    * Called after JavaFX initialized and document loaded
    */
   @FXML
   fun initialize() {
      initializeListTab()
      initializeTableTab()
      initializeFieldTab()
      initializeSortedTableTab()
      hostField.text = "45.56.72.19"
    //  var password = "TravelSourceLLC"
      portField.text = "3306"
  }
   private fun initializeListTab() {
      // Preload some data into listModel, which is then loaded into list via observable property magic
      listModel.add("Lion")
      listModel.add("Bear")
      listModel.add("Giraffe")
      // items are sorted in view
      listModel.add("Giraffe")
      // items are sorted in view
      listModel.add("Bear")
      listModel.add("Giraffe")
      // items are sorted in view
      portField.text = "3306"
  }
//private fun initializeListTab() {
   //   // Preload some data into listModel, which is then loaded into list via observable property magic
  //    listModel.add("Lion")
   //   listModel.add("Bear")
    //  listModel.add("Giraffe")
      // items are sorted in view
      // items are sorted in view
  //    itemListView.items = listModel.sorted()
  // }
   /**
    * Setup model for tableview
    */
   private fun initializeTableTab() {
      // Preload some data
      tableModel.add(SomeProperty("color", "blue"))
      tableModel.add(SomeProperty("gender", "female"))
      tableModel.add(SomeProperty("age", "40"))
      tableModel.add(SomeProperty("city", "Sydney"))
      // items are sorted on property name in view
      tableView.items = tableModel.sorted()
      // map column cell value by simple property lookup by name on SomeProperty type
      }

	@FXML lateinit var historicalView: TableView<MFamousPerson>
	@FXML lateinit var keyword_1: TableColumn<MFamousPerson, String>
	@FXML lateinit var keyword_2: TableColumn<MFamousPerson, String>
	@FXML lateinit var keyword_3: TableColumn<MFamousPerson, String>
	@FXML lateinit var keyword_4: TableColumn<MFamousPerson, String>
	@FXML lateinit var frequency_1: TableColumn<MFamousPerson, String>
	@FXML lateinit var frequency_2: TableColumn<MFamousPerson, String>
	@FXML lateinit var frequency_3: TableColumn<MFamousPerson, String>
	@FXML lateinit var frequency_4: TableColumn<MFamousPerson, String>
	val mFamousPersonModel = FXCollections.observableArrayList<MFamousPerson>()

   private fun initializeFieldTab() {
      // fixed list so just use Strings here
      schemeCombo.items.add("http")
      schemeCombo.items.add("https")
      schemeCombo.selectionModel.select(0)
   }
   fun initDB() { 
   val url = "jdbc:mysql://root:new-password@localhost:3306/TravelSourceLLC?useUnicode=true&serverTimezone=UTC"
   val driver = "com.mysql.cj.jdbc.Driver"
     Database.connect(url, driver)
    }

 class MFamousPerson(val keyword_1: String, val keyword_2: String, val keyword_3: String, val keyword_4: String, val frequency_1: String, val frequency_2: String, val frequency_3: String, val frequency_4: String)

   private fun initializeSortedTableTab() {
    var sortedCategoriesList = categoryListModel.sorted()
    sortedCategoriesList.comparatorProperty().bind(categoryView.comparatorProperty())
    categoryView.items = sortedCategoriesList
       initDB()
    transaction {
    exec("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'Statistica'") { rs -> while(rs.next()) {
         categoryListModel.add(SomeProperty(rs.getString(4), rs.getString(4)))
     category.cellValueFactory = PropertyValueFactory<SomeProperty, String>("value")
        }
      }
}    
//KEYWORD SECTION
keyword_1.cellValueFactory = PropertyValueFactory<MFamousPerson, String>("keyword_1")
frequency_1.cellValueFactory = PropertyValueFactory<MFamousPerson, String>("frequency_1")
keyword_2.cellValueFactory = PropertyValueFactory<MFamousPerson, String>("keyword_2")
frequency_2.cellValueFactory = PropertyValueFactory<MFamousPerson, String>("frequency_2")
keyword_3.cellValueFactory = PropertyValueFactory<MFamousPerson, String>("keyword_3")
frequency_3.cellValueFactory = PropertyValueFactory<MFamousPerson, String>("frequency_3")
keyword_4.cellValueFactory = PropertyValueFactory<MFamousPerson, String>("keyword_4")
frequency_4.cellValueFactory = PropertyValueFactory<MFamousPerson, String>("frequency_4")
 transaction {
    exec("SELECT * from KeywordExpanded limit 100") { rs -> while(rs.next()) {
	var k1 = rs.getString(2)
	if (k1 == null) {  k1 ="-"}
	var f1 = rs.getString(3)
	if (f1 == null) {  f1 ="-"}
	var k2 = rs.getString(4)
	if (k2 == null) {  k2 ="-"}
	var f2 = rs.getString(5)
	if (f2 == null) {  f2 ="-"}
	var k3 = rs.getString(6)
	if (k3 == null) {  k3 ="-"}
	var f3 = rs.getString(7)
	if (f3 == null) {  f3 ="-"}
	var k4 = rs.getString(8)
	if (k4 == null) {  k4 ="-"}
	var f4 = rs.getString(9)
	if (f4 == null) {  f4 ="-"}
	historicalView.items = mFamousPersonModel
	mFamousPersonModel.add(MFamousPerson(k1, k2, k3, k4, f1, f2, f3, f4))
	  }
	 }
	}	 	
 }
     
@FXML
fun filterThisKeyword(){
print(filter.text)	
//mFamousPersonModel.clear()
            val lowerCaseFilter: String = filter.text.toLowerCase()
    val mSortedModel = FXCollections.observableArrayList<MFamousPerson>()
    for( item in mFamousPersonModel) {
        if (item.keyword_1.toLowerCase().contains(filter.text)) {
            mSortedModel.add(MFamousPerson(item.keyword_1, item.frequency_1, item.keyword_2, item.frequency_2, item.keyword_3, item.frequency_3, item.keyword_4, item.frequency_4))
        } else if (item.keyword_2.toLowerCase().contains(filter.text)) {
            mSortedModel.add(MFamousPerson(item.keyword_1, item.frequency_1, item.keyword_2, item.frequency_2, item.keyword_3, item.frequency_3, item.keyword_4, item.frequency_4))
        } else if (item.keyword_3.toLowerCase().contains(filter.text)) {
            mSortedModel.add(MFamousPerson(item.keyword_1, item.frequency_1, item.keyword_2, item.frequency_2, item.keyword_3, item.frequency_3, item.keyword_4, item.frequency_4))
        }else if (item.keyword_4.toLowerCase().contains(filter.text)) {
            mSortedModel.add(MFamousPerson(item.keyword_1, item.frequency_1, item.keyword_2, item.frequency_2, item.keyword_3, item.frequency_3, item.keyword_4, item.frequency_4))
        }
    }
    val sortedData: SortedList<MFamousPerson> = SortedList<MFamousPerson>(mSortedModel)
    historicalView.items=sortedData
 }
    @FXML
    fun filterThisCategory() {
    print(categoryFilter.text)
    }

   @FXML
   fun closeApplication() {
      val stage = itemListView.scene.window as Stage
      stage.close()
   }

   @FXML
   fun addItem() {
      val newItem = newItemField.text.trim()
      if (newItemField.text.trim().isEmpty())
         return
      listModel.add(newItem)
   }

  @FXML
   fun generateUrlClicked() {
      val scheme = schemeCombo.selectionModel.selectedItem
      val host = hostField.text.trim()
      // Cheating here - no real integer validation
      val port = portField.text.trim().toIntOrNull() ?: 80
      urlLabel.text = "$scheme://$host:$port"
   }

}
