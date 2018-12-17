import { element, by, ElementFinder } from 'protractor';

export class EmbarqueComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-embarque div table .btn-danger'));
    title = element.all(by.css('jhi-embarque div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class EmbarqueUpdatePage {
    pageTitle = element(by.id('jhi-embarque-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    codigoRastreoInput = element(by.id('field_codigoRastreo'));
    fechaInput = element(by.id('field_fecha'));
    detallesInput = element(by.id('field_detalles'));
    facturaSelect = element(by.id('field_factura'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCodigoRastreoInput(codigoRastreo) {
        await this.codigoRastreoInput.sendKeys(codigoRastreo);
    }

    async getCodigoRastreoInput() {
        return this.codigoRastreoInput.getAttribute('value');
    }

    async setFechaInput(fecha) {
        await this.fechaInput.sendKeys(fecha);
    }

    async getFechaInput() {
        return this.fechaInput.getAttribute('value');
    }

    async setDetallesInput(detalles) {
        await this.detallesInput.sendKeys(detalles);
    }

    async getDetallesInput() {
        return this.detallesInput.getAttribute('value');
    }

    async facturaSelectLastOption() {
        await this.facturaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async facturaSelectOption(option) {
        await this.facturaSelect.sendKeys(option);
    }

    getFacturaSelect(): ElementFinder {
        return this.facturaSelect;
    }

    async getFacturaSelectedOption() {
        return this.facturaSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class EmbarqueDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-embarque-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-embarque'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
