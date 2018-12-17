import { element, by, ElementFinder } from 'protractor';

export class ElementoOrdenComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-elemento-orden div table .btn-danger'));
    title = element.all(by.css('jhi-elemento-orden div h2#page-heading span')).first();

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

export class ElementoOrdenUpdatePage {
    pageTitle = element(by.id('jhi-elemento-orden-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    cantidadInput = element(by.id('field_cantidad'));
    precioTotalInput = element(by.id('field_precioTotal'));
    estatusSelect = element(by.id('field_estatus'));
    proveedorDInput = element(by.id('field_proveedorD'));
    contratoMarcoDInput = element(by.id('field_contratoMarcoD'));
    contratoMarcoSelect = element(by.id('field_contratoMarco'));
    proveedorSelect = element(by.id('field_proveedor'));
    ordenCompraSelect = element(by.id('field_ordenCompra'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCantidadInput(cantidad) {
        await this.cantidadInput.sendKeys(cantidad);
    }

    async getCantidadInput() {
        return this.cantidadInput.getAttribute('value');
    }

    async setPrecioTotalInput(precioTotal) {
        await this.precioTotalInput.sendKeys(precioTotal);
    }

    async getPrecioTotalInput() {
        return this.precioTotalInput.getAttribute('value');
    }

    async setEstatusSelect(estatus) {
        await this.estatusSelect.sendKeys(estatus);
    }

    async getEstatusSelect() {
        return this.estatusSelect.element(by.css('option:checked')).getText();
    }

    async estatusSelectLastOption() {
        await this.estatusSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setProveedorDInput(proveedorD) {
        await this.proveedorDInput.sendKeys(proveedorD);
    }

    async getProveedorDInput() {
        return this.proveedorDInput.getAttribute('value');
    }

    async setContratoMarcoDInput(contratoMarcoD) {
        await this.contratoMarcoDInput.sendKeys(contratoMarcoD);
    }

    async getContratoMarcoDInput() {
        return this.contratoMarcoDInput.getAttribute('value');
    }

    async contratoMarcoSelectLastOption() {
        await this.contratoMarcoSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async contratoMarcoSelectOption(option) {
        await this.contratoMarcoSelect.sendKeys(option);
    }

    getContratoMarcoSelect(): ElementFinder {
        return this.contratoMarcoSelect;
    }

    async getContratoMarcoSelectedOption() {
        return this.contratoMarcoSelect.element(by.css('option:checked')).getText();
    }

    async proveedorSelectLastOption() {
        await this.proveedorSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async proveedorSelectOption(option) {
        await this.proveedorSelect.sendKeys(option);
    }

    getProveedorSelect(): ElementFinder {
        return this.proveedorSelect;
    }

    async getProveedorSelectedOption() {
        return this.proveedorSelect.element(by.css('option:checked')).getText();
    }

    async ordenCompraSelectLastOption() {
        await this.ordenCompraSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async ordenCompraSelectOption(option) {
        await this.ordenCompraSelect.sendKeys(option);
    }

    getOrdenCompraSelect(): ElementFinder {
        return this.ordenCompraSelect;
    }

    async getOrdenCompraSelectedOption() {
        return this.ordenCompraSelect.element(by.css('option:checked')).getText();
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

export class ElementoOrdenDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-elementoOrden-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-elementoOrden'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
