import { element, by, ElementFinder } from 'protractor';

export class OrdenCompraComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-orden-compra div table .btn-danger'));
    title = element.all(by.css('jhi-orden-compra div h2#page-heading span')).first();

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

export class OrdenCompraUpdatePage {
    pageTitle = element(by.id('jhi-orden-compra-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    fechaEntradaInput = element(by.id('field_fechaEntrada'));
    estatusSelect = element(by.id('field_estatus'));
    codigoInput = element(by.id('field_codigo'));
    lugarEntregaDInput = element(by.id('field_lugarEntregaD'));
    lugarEntregaSelect = element(by.id('field_lugarEntrega'));
    dependenciaSelect = element(by.id('field_dependencia'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setFechaEntradaInput(fechaEntrada) {
        await this.fechaEntradaInput.sendKeys(fechaEntrada);
    }

    async getFechaEntradaInput() {
        return this.fechaEntradaInput.getAttribute('value');
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

    async setCodigoInput(codigo) {
        await this.codigoInput.sendKeys(codigo);
    }

    async getCodigoInput() {
        return this.codigoInput.getAttribute('value');
    }

    async setLugarEntregaDInput(lugarEntregaD) {
        await this.lugarEntregaDInput.sendKeys(lugarEntregaD);
    }

    async getLugarEntregaDInput() {
        return this.lugarEntregaDInput.getAttribute('value');
    }

    async lugarEntregaSelectLastOption() {
        await this.lugarEntregaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async lugarEntregaSelectOption(option) {
        await this.lugarEntregaSelect.sendKeys(option);
    }

    getLugarEntregaSelect(): ElementFinder {
        return this.lugarEntregaSelect;
    }

    async getLugarEntregaSelectedOption() {
        return this.lugarEntregaSelect.element(by.css('option:checked')).getText();
    }

    async dependenciaSelectLastOption() {
        await this.dependenciaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async dependenciaSelectOption(option) {
        await this.dependenciaSelect.sendKeys(option);
    }

    getDependenciaSelect(): ElementFinder {
        return this.dependenciaSelect;
    }

    async getDependenciaSelectedOption() {
        return this.dependenciaSelect.element(by.css('option:checked')).getText();
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

export class OrdenCompraDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-ordenCompra-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-ordenCompra'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
