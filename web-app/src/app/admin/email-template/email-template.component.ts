import { EmailTemplateService } from "./../../_services/admin/email-template.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { EmailTemplate } from "./../../_model/email-template.model";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "sa-email-template",
  templateUrl: "./email-template.component.html",
  styleUrls: ["./email-template.component.scss"]
})
export class EmailTemplateComponent implements OnInit {
  listOfName = [
    { text: "Joe", value: "Joe", byDefault: true },
    { text: "Jim", value: "Jim" }
  ];
  listOfAddress = [
    { text: "London", value: "London", byDefault: true },
    { text: "Sidney", value: "Sidney" }
  ];
  listOfSearchName = ["Joe"]; // You need to change it as well!
  sortName: string | null = null;
  sortValue: string | null = null;
  searchAddress = "London";
  listOfData: Array<EmailTemplate> = [];
  // You need to change it as well!
  listOfDisplayData: Array<EmailTemplate> = [];

  isAddModel = false;
  isAddLoading = false;

  addItemForm: FormGroup;

  listOfTemplate;

  public constructor(
    private fb: FormBuilder,
    private emailService: EmailTemplateService
  ) {}

  ngOnInit(): void {
    this.addItemForm = this.fb.group({
      key: ["", [Validators.required]],
      template: ["", [Validators.required]],
      description: [null]
    });
    this.getAllTemplates();
  }

  getAllTemplates() {
    this.emailService.getAll().subscribe(reponse => {
      this.listOfDisplayData = reponse;
    });
  }

  sort(sort: { key: string; value: string }): void {
    this.sortName = sort.key;
    this.sortValue = sort.value;
    this.search();
  }

  filter(listOfSearchName: string[], searchAddress: string): void {
    console.log(listOfSearchName, searchAddress);
    this.listOfSearchName = listOfSearchName;
    this.searchAddress = searchAddress;
    this.search();
  }

  search(): void {
    // /** filter data **/
    // const filterFunc = (item: { name: string; age: number; address: string }) =>
    //   (this.searchAddress ? item.address.indexOf(this.searchAddress) !== -1 : true) &&
    //   (this.listOfSearchName.length ? this.listOfSearchName.some(name => item.name.indexOf(name) !== -1) : true);
    // const data = this.listOfData.filter(item => filterFunc(item));
    // /** sort data **/
    // if (this.sortName && this.sortValue) {
    //   this.listOfDisplayData = data.sort((a, b) =>
    //     this.sortValue === 'ascend'
    //       ? a[this.sortName!] > b[this.sortName!]
    //         ? 1
    //         : -1
    //       : b[this.sortName!] > a[this.sortName!]
    //       ? 1
    //       : -1
    //   );
    // } else {
    //   this.listOfDisplayData = data;
    // }
  }

  addTemplate(): void {
    this.isAddModel = true;
  }

  submitAddingItem(): void {
    this.isAddLoading = true;
    for (const i in this.addItemForm.controls) {
      this.addItemForm.controls[i].markAsDirty();
      this.addItemForm.controls[i].updateValueAndValidity();
    }

    let model = new EmailTemplate();
    model.key = this.addItemForm.get(["key"]).value;
    model.template = this.addItemForm.get(["template"]).value;
    model.description = this.addItemForm.get(["description"]).value;
    this.emailService.addTemplate(model).subscribe(
      sucess => {

        this.getAllTemplates();
        this.isAddLoading = false;
        this.isAddModel = false;
      },
      error => {

        this.isAddLoading = false;
        this.isAddModel = false;
      }
    );
  }

  addCancel(): void {
    console.log("Button cancel clicked!");
    this.isAddModel = false;
  }
}
